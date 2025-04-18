package com.project.downloadbooks.domain.model

data class VolumeInfoDomain(
  val title: String?,
  val authors: List<String>?,
  val description: String?,
  val imageLinksDomain: ImageLinksDomain?
){
  companion object {
    val emptyVolumeInfo = VolumeInfoDomain(
      title = "",
      authors = emptyList(),
      description = "",
      imageLinksDomain = null
    )
  }
}