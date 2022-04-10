package com.task.noteapp.extension

import com.task.noteapp.domain.usecase.DD_MM_YYYY_FORMAT
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

fun OffsetDateTime.format(): String {
    return DateTimeFormatter.ofPattern(DD_MM_YYYY_FORMAT)
        .withZone(ZoneId.systemDefault())
        .format(this)
}