import java.awt.Color

class Global {
    val defaultColor: Int
        get() = Color.parseColor(DEFAULT_COLOR)

    companion object {
        @JvmStatic
        var instance: Global? = null
            get() {
                if (field == null) {
                    field = Global()
                }
                return field
            }
            private set

        private const val DEFAULT_COLOR = "#E44D4D" //red
    }
}
