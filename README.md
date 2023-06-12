# **Machine Learning Path**

## **Description**
Travelee is the idea of an application that provides recommendations for ecotourism places that match the user's preferences based on information about the characteristics of the place.

## **Modeling**
In the development stage of the recommendation system machine learning model, content-based and collaborative filtering recommendations are used to provide the best recommendations to users according to user preferences.

  **1. Content-based Filtering Recommendation**

Content-based filtering is used to recommend ecotourism places that have similar categories to ecotourism places that have been favored by users. Some of the steps taken to create a recommendation system with content-based filtering include the following.

* TF-IDF Vectorizer
TF-IDF Vectorizer will convert the text of the place name into a numerical representation in matrix form.

* Cosine Similarity
Cosine similarity is used to measure the level of similarity between two place data by calculating the angle between the two data. This method produces a value that indicates the level of similarity between two place data, where a value close to 1 indicates a high level of similarity, while a value close to 0 indicates a low level of similarity.

* Best Recommended Results
After the tourist attraction data is transformed into a matrix using TF-IDF Vectorizer, and the similarity between place names is determined using cosine similarity, the next step is to conduct testing on the recommendation system using the content-based filtering approach. The following are the observed results of the testing:

  **2. Collaborative Filtering Recommendation**
