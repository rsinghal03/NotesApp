package com.task.noteapp.domain.usecase

import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

const val DD_MM_YYYY_FORMAT = "dd/mm/yyyy"

class FormatDate {

    /**
     * Format to HH:MM:SS
     * params: time - in millisecond
     */
    fun formatDate(time: OffsetDateTime): String {
        return DateTimeFormatter.ofPattern(DD_MM_YYYY_FORMAT)
            .withZone(ZoneId.of("UTC"))
            .format(time)
    }
}