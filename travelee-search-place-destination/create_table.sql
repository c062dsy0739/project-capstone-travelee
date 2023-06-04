CREATE TABLE place_destination (
    "place_id" INT(10) NOT NULL,
    PRIMARY KEY(place_id),
    "place_name" VARCHAR(255) NOT NULL,
    "place_description" VARCHAR(255) NOT NULL,
    "category" VARCHAR(255) NOT NULL,
    "city" VARCHAR(255) NOT NULL,
    "price" VARCHAR(255) NOT NULL,
    "rating" VARCHAR(255) NOT NULL,
    "description_location" VARCHAR(255) NOT NULL,
    "place_img" VARCHAR(255) NOT NULL,
    "gallery_photo_img1" VARCHAR(255) NOT NULL,
    "gallery_photo_img2" VARCHAR(255) NOT NULL,
    "gallery_photo_img3" VARCHAR(255) NOT NULL
);
