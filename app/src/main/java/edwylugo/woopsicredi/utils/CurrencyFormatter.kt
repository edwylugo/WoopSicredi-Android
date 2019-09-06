package edwylugo.woopsicredi.utils

import java.text.DecimalFormat

object CurrencyFormatter {

    private val formatter = DecimalFormat("R$ #.00")

    fun toFormat(price: Double): String {
        return formatter.format(price).replace(".", ",")
    }
}