package com.example.nurseduty

object KoreanHolidays {
    // Google Calendar (ko.south_korea#holiday@group.v.calendar.google.com) 기준 공휴일
    private val holidays = mapOf(
        "2026-01-01" to "새해첫날",
        "2026-02-16" to "설날 연휴",
        "2026-02-17" to "설날",
        "2026-02-18" to "설날 연휴",
        "2026-03-01" to "삼일절",
        "2026-03-02" to "대체(삼일절)",
        "2026-05-01" to "노동절",
        "2026-05-05" to "어린이날",
        "2026-05-24" to "부처님오신날",
        "2026-05-25" to "대체(부처님)",
        "2026-06-03" to "지방선거일",
        "2026-06-06" to "현충일",
        "2026-07-17" to "제헌절",
        "2026-08-15" to "광복절",
        "2026-08-17" to "대체(광복절)",
        "2026-09-24" to "추석 연휴",
        "2026-09-25" to "추석",
        "2026-09-26" to "추석 연휴",
        "2026-10-03" to "개천절",
        "2026-10-05" to "대체(개천절)",
        "2026-10-09" to "한글날",
        "2026-12-25" to "크리스마스"
    )

    fun getHolidayName(dateKey: String): String? = holidays[dateKey]
}
