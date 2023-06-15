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
| place_name                |category |
| :-------------------------|:-------:|
| Candi Gedong Songo	       | Budaya  |
| Setu Babakan	             | Budaya  |
| Desa Wisata Sade	         | Budaya  |
| Museum Ullen Sentalu	     | Budaya  |
| Desa Wisata Jodipan	      | Budaya  |

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

  Based on the results of the place recommendations, it can be seen that the recommendation system takes a random user (62), then searches for places with the best rating from the user.
  
  - Taman Wisata Alam Gunung Geulis: Budaya, Cagar Alam
  - Taman Nasional Berbak: Cagar Alam, Taman Nasional
  - Hutan Mangrove Kulon Progo: Cagar Alam
  - Taman Nasional Komodo: Bahari, Taman Hiburan, Taman Nasional
  - Pantai Samas: Bahari, Cagar Alam

  Furthermore, the system will display a list of 10 recommended ecotourism places based on the category that the random user had. It can be seen that the system recommends several places with the same category.
  
  - Desa Wisata Jodipan: Budaya
  - Taman Wisata Alam Puncak Banyu Biru: Budaya, Cagar Alam
  - Taman Wisata Alam Hutan Albasia: Budaya, Cagar Alam
  - Taman Nasional Kutai: Cagar Alam, Taman Nasional
  - Pantai Watu Kodok: Bahari, Cagar Alam
  - Pantai Wediombo: Bahari, Cagar Alam
  - Puncak Gunung Api Purba - Nglanggeran: Cagar Alam
  - Puncak Pinus Becici: Cagar Alam, Taman Hiburan
  - Sindu Kusuma Edupark (SKE): Cagar Alam, Taman Hiburan
  - Watu Lumbung: Cagar Alam


## **Conclusion**

  It can be concluded that the system successfully recommends both content-based filtering and collaborative filtering. Collaborative filtering requires place rating data from users, while in content-based filtering, rating data is not needed because the system will recommend based on the content of the place, namely the category.

## **Reference**

Building Content-Based Filtering Recommendation System in [here](https://www.kdnuggets.com/2020/07/building-content-based-book-recommendation-engine.html)
Building Collaborative Filtering Recommendation System in [here](https://gilberttanner.com/blog/building-a-book-recommendation-system-usingkeras/)
Integrate Machine Learning Model Deployment Using Flask in [here](https://medium.com/analytics-vidhya/deploying-your-machine-learning-model-as-a-rest-api-using-flask-c2e6a0b574f5)


