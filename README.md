# **Machine Learning Path**

## **Description**
Travelee is the idea of an application that provides recommendations for ecotourism places that match the user's preferences based on information about the characteristics of the place.

## Dataset
To build our recommendation system, we were inspired by this data [here](https://www.kaggle.com/datasets/azharianisah/infotempatwisata). However, due to the unavailability of suitable datasets for our project, we made the decision to create our own dataset. We conducted data crawling from various sources and compiled it into a unified dataset, which you can access/view [here](https://docs.google.com/spreadsheets/d/1LYUxO6RhsTFx_NpP7jZO3RmqXP51CP_JjiNMqDSA27Q/edit?amp;usp=embed_facebook#gid=195252240)

## Library
* tensorflow
* numpy
* pandas
* scikit-learn
* matplotlib

## **Modeling**
In the development stage of the recommendation system machine learning model, content-based and collaborative filtering recommendations are used to provide the best recommendations to users according to user preferences.

  **1. Content-based Filtering Recommendation**

Content-based filtering is used to recommend ecotourism places that have similar categories to ecotourism places that have been favored by users. Some of the steps taken to create a recommendation system with content-based filtering include the following.

* TF-IDF Vectorizer
TF-IDF Vectorizer will convert the text of the place name into a numerical representation in matrix form.

* Cosine Similarity
Cosine similarity is used to measure the level of similarity between two place data by calculating the angle between the two data. This method produces a value that indicates the level of similarity between two place data, where a value close to 1 indicates a high level of similarity, while a value close to 0 indicates a low level of similarity.

* Recommendation Result
After the tourist attraction data is transformed into a matrix using TF-IDF Vectorizer, and the similarity between place names is determined using cosine similarity, the next step is to conduct testing on the recommendation system using the content-based filtering approach. The following are the observed results of the testing:

A place name selected by the user is retrieved.

**Table 1. User-selected Place Name**
| place_name                |category |
| :-------------------------|:-------:|
| Saung Angklung Mang Udjo  | Budaya  |

Here are the results of place name recommendations based on the same category.

**Table 2. Content-based Filtering Recommendation Results**
<img width="422" alt="image" src="https://github.com/c062dsy0739/project-capstone-travelee/assets/83300254/716c38f7-6ebe-410d-8e7e-9d45e3e31fb1">

Based on the recommendation results above, it can be seen that the system created successfully provides recommendations for a place, namely 'Saung Angklung Mang Udjo' and generated place recommendations with the same category, namely culture.

**2. Collaborative Filtering Recommendation**
Collaborative Filtering Recommendation is usually done using algorithms that classify users based on preferences and similarities with other users, then use that information to provide recommendations suitable for that user. Tahap-tahap yang dilakukan untuk membuat sistem rekomendasi dengan collaborative filtering diantaranya sebagai berikut.

* Data Preparation
The process of encoding the user_id and place_id variables in the eco_rating dataset into an array. Then the encoding results will be mapped or mapping features that have been encoded into the eco_rating dataset. Based on the encoding and mapping results, the number of users is 156, the number of places is 182, the minimum rating value is 2.0, and the maximum rating value is 5.0.

* Split Training Data and Validation Data
The dataset split process starts by randomizing the eco_rating dataset, then separating it into training data and validation data with a ratio of 80:20.

* Model Development
From the machine learning model that has been built using embedding and regularizer layers, as well as adam optimizer, Mean Squared Error loss function, and RMSE (Root Mean Squared Error) metrics, the results of testing the ecotourism recommendation system with a collaborative filtering recommendation system are obtained.

* Recommendation Result
Berdasarkan hasil rekomendasi tempat di atas, dapat dilihat bahwa sistem rekomendasi mengambil pengguna acak (62), lalu dilakukan pencarian tempat dengan rating terbaik dari user tersebut.
<img width="311" alt="image" src="https://github.com/c062dsy0739/project-capstone-travelee/assets/83300254/613e309c-47e1-420b-9762-f4e8171cbf6b">

Selanjutnya, sistem akan menampilkan 10 daftar tempat ecotourism yang direkomendasikan berdasarkan kategori yang dimiliki terhadap pengguna acak tadi. Berdasarkan gambar di bawah ini, dapat dilihat bahwa sistem merekomendasikan beberapa tempat dengan kategori yang sama.
<img width="287" alt="image" src="https://github.com/c062dsy0739/project-capstone-travelee/assets/83300254/bdf4b04c-1c96-401b-b059-d9503e7f7a2e">

