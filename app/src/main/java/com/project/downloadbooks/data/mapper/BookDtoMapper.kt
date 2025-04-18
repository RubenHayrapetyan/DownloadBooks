package com.project.downloadbooks.data.mapper

import com.project.downloadbooks.data.model.remote.BookDto
import com.project.downloadbooks.domain.model.BookDomain
import com.project.downloadbooks.domain.model.VolumeInfoDomain

internal fun BookDto.toDomain() = BookDomain(
  id = this.id,
  volumeInfo = this.volumeInfo?.toDomain() ?: VolumeInfoDomain.emptyVolumeInfo
)