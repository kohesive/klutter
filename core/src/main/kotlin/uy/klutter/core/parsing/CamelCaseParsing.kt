package uy.klutter.core.parsing

fun String?.splitOnCamelCase(): List<String> {
    // split camel cases, with underscores also acting as split point then ignored
    // Convert things that are proceeded by "by" to a variable ":variable"  (ByAge = /:age/)
    // Convert things that are proceeded by "with" to a path element + following variable (WithName = /name/:name/)
    //
    // thisIsATestOfSplitting = this is a test of splitting
    // AndWhatAboutThis = and what about this
    // aURIIsPresent = a uri is present
    // SomethingBySomething = something :something
    // something20BySomething30 = something20 :something30
    // 20ThisAndThat = 20 this and that
    // 20thisAndThat = 20this and that
    // What_about_underscores = what about underscores
    // 20_ThisAndThat_And_What = 20 this and that and what
    // 20________thisAndThat__What = 20 this and that what
    //
    if (this == null) return emptyList()

    val result = arrayListOf<String>()
    val uppers = StringBuilder(this.length)
    val mixed = StringBuilder(this.length)

    fun takeUpperLowerWord() {
        if (uppers.length == 0 && mixed.length == 0) return
        val word = uppers.toString().toLowerCase() + mixed.toString()
        uppers.setLength(0)
        mixed.setLength(0)
        if (word.isNotBlank()) {
            result.add(word)
        }
    }

    this.forEach { ch ->
        if (ch == '_') {
            takeUpperLowerWord()
        }
        else {
            val lowerCh = ch.toLowerCase()
            if (ch == lowerCh) {
                if (uppers.length > 1) {
                    // previous uppers are a word except for the last one, which joins these lowers
                    val hangingUpper = uppers[uppers.length - 1]
                    uppers.setLength(uppers.length - 1)
                    takeUpperLowerWord()
                    mixed.append(hangingUpper.toLowerCase())
                }
                mixed.append(ch)
            } else {
                if (mixed.length > 0) {
                    // previous lowers are a word on their own
                    takeUpperLowerWord()
                }
                uppers.append(ch)
            }
        }
    }
    takeUpperLowerWord()
    return result
}
