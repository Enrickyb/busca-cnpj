package br.com.buscacnpj.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.addCnpjMask() {
    val mask = "##.###.###/####-##"

    this.addTextChangedListener(object : TextWatcher {
        private var isUpdating = false

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (isUpdating) return

            val unmaskedText = s.toString().replace(Regex("[^0-9]"), "")
            val maskedText = applyMask(unmaskedText, mask)

            isUpdating = true
            this@addCnpjMask.setText(maskedText)
            this@addCnpjMask.setSelection(calculateCursorPosition(start, before, count, maskedText))
            isUpdating = false
        }

        override fun afterTextChanged(s: Editable?) {}

        private fun applyMask(unmaskedText: String, mask: String): String {
            val result = StringBuilder()
            var index = 0

            for (char in mask) {
                if (char == '#' && index < unmaskedText.length) {
                    result.append(unmaskedText[index])
                    index++
                } else if (char != '#') {
                    result.append(char)
                }
            }

            return result.toString()
        }

        private fun calculateCursorPosition(start: Int, before: Int, count: Int, maskedText: String): Int {
            val newCursorPosition = start + count

            // Prevenir erros ao apagar ou digitar quando o cursor está em posições fixas da máscara
            if (count == 0) { // Apagando
                return start.coerceAtLeast(0)
            }
            if (newCursorPosition < maskedText.length && maskedText[newCursorPosition].isDigit().not()) {
                return newCursorPosition + 1
            }

            return newCursorPosition.coerceAtMost(maskedText.length)
        }
    })
}
