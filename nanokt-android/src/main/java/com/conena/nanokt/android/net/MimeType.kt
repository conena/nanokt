/*
 * Copyright (C) 2023 Fabian Andera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

@file:Suppress("unused")

package com.conena.nanokt.android.net

/**
 * Provides some commonly used MIME types as constants.
 */
object MimeType {

    const val JSON = "application/json"
    const val PDF = "application/pdf"
    const val RTF = "application/rtf"
    const val UNKNOWN = "application/octet-stream"

    const val TEXT = "text/*"
    const val PLAIN_TEXT = "text/plain"
    const val CSV = "text/csv"
    const val HTML = "text/html"
    const val MARKDOWN = "text/markdown"
    const val URI_LIST = "text/uri-list"

    const val VIDEO = "video/*"
    const val VIDEO_MP4 = "video/mp4"
    const val VIDEO_H264 = "video/h264"
    const val VIDEO_WEBM = "video/webm"

    const val AUDIO = "audio/*"
    const val AUDIO_MP3 = "audio/mpeg"
    const val AUDIO_MP4 = "audio/mp4"
    const val AUDIO_WAV = "audio/wav"
    const val AUDIO_AAC = "audio/x-aac"

    const val IMAGE = "image/*"
    const val IMAGE_BITMAP = "image/bmp"
    const val IMAGE_GIF = "image/gif"
    const val IMAGE_JPEG = "image/jpeg"
    const val IMAGE_PNG = "image/png"
    const val IMAGE_SVG = "image/svg+xml"
    const val IMAGE_TIF = "image/tiff"
    const val IMAGE_WEBP = "image/webp"

    const val MAIL_RFC822 = "message/rfc822"

    const val ANDROID_APK = "application/vnd.android.package-archive"
    const val ANDROID_ACTIVITY = "application/vnd.android.activity"
    const val ANDROID_INTENT = "text/vnd.android.intent"
    const val ANDROID_SHORTCUT = "application/vnd.android.shortcut"
    const val ANDROID_TASK = "application/vnd.android.task"

}