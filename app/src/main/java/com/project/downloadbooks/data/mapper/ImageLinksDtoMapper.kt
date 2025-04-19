package com.project.downloadbooks.data.mapper

import com.project.downloadbooks.data.model.remote.ImageLinksDto
import com.project.downloadbooks.domain.model.ImageLinksDomain

internal fun ImageLinksDto.toDomain() = this.thumbnail?.let { ImageLinksDomain(thumbnail = it) }