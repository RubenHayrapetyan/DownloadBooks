package com.project.downloadbooks.presentation.mapper

import com.project.downloadbooks.domain.model.VolumeInfoDomain
import com.project.downloadbooks.presentation.model.VolumeInfoUi
import com.project.downloadbooks.presentation.util.toHttps

internal fun VolumeInfoDomain.toUi() = VolumeInfoUi(
  title = this.title ?: "",
  authors = this.authors ?: emptyList(),
  description = this.description ?: "",
  imageLink = this.imageLinksDomain?.thumbnail.toHttps()
)