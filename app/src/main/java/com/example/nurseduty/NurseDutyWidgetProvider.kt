package com.example.nurseduty

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.RemoteViews
import java.time.LocalDate

class NurseDutyWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val prefs = context.getSharedPreferences("nurse_duty_prefs", Context.MODE_PRIVATE)
        val today = LocalDate.now()
        val korDow = arrayOf("월", "화", "수", "목", "금", "토", "일")

        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_nurse_duty_5x1)

            val dayIds = intArrayOf(R.id.day_1_title, R.id.day_2_title, R.id.day_3_title, R.id.day_4_title, R.id.day_5_title)
            val shiftIds = intArrayOf(R.id.day_1_shift, R.id.day_2_shift, R.id.day_3_shift, R.id.day_4_shift, R.id.day_5_shift)
            val holIds = intArrayOf(R.id.day_1_holiday, R.id.day_2_holiday, R.id.day_3_holiday, R.id.day_4_holiday, R.id.day_5_holiday)

            for (i in 0 until 5) {
                val d = today.plusDays(i.toLong())
                val key = d.toString()
                val dowStr = korDow[d.dayOfWeek.value - 1]
                val shift = prefs.getString("shift_$key", if (i == 0) "NO" else "D") ?: "D"
                val holidayName = KoreanHolidays.getHolidayName(key)

                views.setTextViewText(dayIds[i], "${d.monthValue}/${d.dayOfMonth}($dowStr)")
                views.setTextViewText(shiftIds[i], shift)
                views.setTextViewText(holIds[i], holidayName ?: "")

                if (holidayName != null || d.dayOfWeek.value == 7) {
                    views.setTextColor(dayIds[i], Color.parseColor("#FDA4AF"))
                } else if (d.dayOfWeek.value == 6) {
                    views.setTextColor(dayIds[i], Color.parseColor("#93C5FD"))
                } else {
                    views.setTextColor(dayIds[i], Color.WHITE)
                }
            }

            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            views.setOnClickPendingIntent(R.id.widget_root, pendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
