package com.project.downloadbooks.data.mapper

import com.project.downloadbooks.data.model.remote.VolumeInfoDto
import com.project.downloadbooks.domain.model.VolumeInfoDomain

internal fun VolumeInfoDto.toDomain() = VolumeInfoDomain(
  title = this.title,
  authors = this.authors ?: emptyList(),
  description = this.description,
  imageLinksDomain = this.imageLinksDto?.toDomain()
)