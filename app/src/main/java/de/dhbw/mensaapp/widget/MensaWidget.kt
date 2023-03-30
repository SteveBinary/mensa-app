package de.dhbw.mensaapp.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import de.dhbw.mensaapp.R


class MensaWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateDisplayedOrdered(context, -1)
        }
    }

    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)

        if (context != null) {
            updateDisplayedOrdered(context, -1)
        }
        onUpdate(context!!, appWidgetManager!!, IntArray(appWidgetId))
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
        updateDisplayedOrdered(context, -1)
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) {
            return
        }

        if (intent != null && intent.action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            val newOrderCount = intent.getIntExtra(AppWidgetManager.EXTRA_CUSTOM_EXTRAS, Int.MIN_VALUE)

            if (newOrderCount != Int.MIN_VALUE) {
                updateDisplayedOrdered(context, newOrderCount)
                return
            }
        }

        super.onReceive(context, intent)
    }

    private fun updateDisplayedOrdered(context: Context, newOrderCount: Int) {
        val views = RemoteViews(context.packageName, R.layout.mensa_widget)

        val widgetMessage = if (newOrderCount <= 0) {
            context.getString(R.string.default_widget_text)
        } else if (newOrderCount == 1) {
            context.getString(R.string.widget_text_singular)
        } else {
            context.getString(R.string.widget_text_plural, newOrderCount)
        }

        views.setTextViewText(R.id.tvOrderedMeals, widgetMessage)

        val mensaWidget = ComponentName(context, MensaWidget::class.java)
        val widgetManager = AppWidgetManager.getInstance(context)
        widgetManager.updateAppWidget(mensaWidget, views)
    }
}

fun sendNewOrderCountToWidget(context: Context, newOrderCount: Int) {
    val intent = Intent(context, MensaWidget::class.java).apply {
        action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        val ids: IntArray = AppWidgetManager.getInstance(context).getAppWidgetIds(
            ComponentName(context, MensaWidget::class.java)
        )

        putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        putExtra(AppWidgetManager.EXTRA_CUSTOM_EXTRAS, newOrderCount)
    }

    context.sendBroadcast(intent)
}
