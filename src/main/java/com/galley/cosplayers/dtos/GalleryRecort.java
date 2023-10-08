package com.galley.cosplayers.dtos;

import io.micrometer.common.lang.NonNull;

public record GalleryRecort(@NonNull String nome,@NonNull String description,@NonNull String imageurl,@NonNull String thumbnailurl,@NonNull String datacriacao,@NonNull String users) {
}
