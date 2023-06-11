import os
import json
import numpy as np
import pandas as pd
import requests
import tensorflow as tf
from flask import Flask, request, jsonify
from keras.models import load_model
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
from google.cloud import storage
from google.oauth2 import service_account
import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

# Inisialisasi aplikasi Firebase (pastikan Anda memiliki file serviceAccountKey.json yang sesuai)
cred = credentials.Certificate("serviceAccountKey.json")
firebase_admin.initialize_app(cred)

# Buat objek database Firestore
db = firestore.client()

app = Flask(__name__)

# Load data
eco_place = pd.read_csv('eco_place.csv')
eco_rating = pd.read_csv('eco_rating.csv')

# Content
items = eco_place[['place_name', 'category']]

# Collaborative
model = tf.keras.models.load_model('model_saved')
df_places = eco_place
df_ratings = eco_rating
user_id = eco_rating['user_id'].unique().tolist()
user_to_num_encoded = {x: i for i, x in enumerate(user_id)}
num_encoded_to_user = {i: x for i, x in enumerate(user_id)}
places_id = eco_rating['place_id'].unique().tolist()
place_to_num_encoded = {x: i for i, x in enumerate(places_id)}
num_encoded_to_place = {i: x for i, x in enumerate(places_id)}

eco_rating['rating'] = eco_rating['user_rating'].values.astype(np.float32)

# Content-based for user preferences after registration - PREDICT 1
def place_recommendations(user_categories):
    categories = eco_place['category'].tolist()
    user_preferences = user_categories

    all_text = categories + user_preferences

    vectorizer = TfidfVectorizer()
    tfidf_matrix = vectorizer.fit_transform(all_text)
    similarity_matrix = cosine_similarity(tfidf_matrix[-len(user_preferences):], tfidf_matrix[:-len(user_preferences)])
    top_indices = np.argsort(similarity_matrix.mean(axis=0))[-10:][::-1]
    recommended_place = eco_place.iloc[top_indices].copy()
    return recommended_place.to_dict(orient='records')

# Routing - user preferences
@app.route('/predict1/preference/<user_id>', methods=['GET'])
def predict1(user_id):
    try:
        # Mengambil data dari Firestore berdasarkan user_id
        user_ref = db.collection("users").where("user_id", "==", int(user_id))
        user_docs = user_ref.get()

        if len(user_docs) > 0:
            user_data = user_docs[0].to_dict()
            user_category = user_data.get('user_category', [])

            # Lakukan operasi atau pemrosesan berdasarkan user_id dan user_category
            recommended_places = place_recommendations(user_category)

            return jsonify({'user_id': user_id, 'user_category': user_category, 'recommended_places': recommended_places})
        else:
            return jsonify({'message': 'User not found.'}), 404
    except Exception as e:
        return jsonify({'message': 'Error processing request.', 'error': str(e)}), 500


#COLLABORATIVE PREDICT 3
def recommend_places(user_id):
    place_rated = df_ratings[df_ratings.user_id == user_id]
    
    place_not_rated = df_places[~df_places['place_id'].isin(place_rated.place_id.values)]['place_id']
    place_not_rated = list(set(place_not_rated).intersection(set(place_to_num_encoded.keys())))
    
    # Tambahkan pengecekan untuk nilai None
    place_not_rated = [place_to_num_encoded.get(x) for x in place_not_rated if x is not None]
    if len(place_not_rated) == 0:
        return []
    
    place_not_rated = [[x] for x in place_not_rated]
    user_encoder = user_to_num_encoded.get(user_id)
    
    # Ubah user_encoder menjadi array NumPy
    user_place_array = np.hstack(([[user_encoder]] * len(place_not_rated), place_not_rated))
    
    # Ubah user_place_array menjadi tipe data int64
    user_place_array = np.array(user_place_array, dtype=np.int64)  # Perubahan tipe data
    
    ratings = model.predict(user_place_array).flatten()
    
    top_ratings_indices = ratings.argsort()[-10:][::-1]
    recommended_place_ids = [num_encoded_to_place.get(place_not_rated[x][0]) for x in top_ratings_indices]
    
    recommendations = []
    
    top_place_user = (place_rated.sort_values(by='rating', ascending=False)
                      .head(5)
                      .place_id.values)
    
    rows_df_places = df_places[df_places['place_id'].isin(top_place_user)]
    for row in rows_df_places.itertuples():
        recommendations.append({
            "place_name": row.place_name,
            "category": row.category
        })

    recommended_places = df_places[df_places['place_id'].isin(recommended_place_ids)]
    for row in recommended_places.itertuples():
        recommendations.append({
            "place_name": row.place_name,
            "category": row.category
        })
        
    return recommendations

#Routing CC
@app.route('/predict3/popular-place', methods=['GET'])
def predict3():
    data = request.get_json()  # Get the input data from the request
    
    # Extract the input parameters
    user_id = data['user_id']
    
    # Call the recommend_places function
    recommendations = recommend_places(user_id)
    
    # Return the JSON response
    return jsonify(recommendations)

@app.route('/')
def hello_world():
    return 'Hello world'
 
if __name__ == '__main__':
    # Start the Flask app
    app.run()