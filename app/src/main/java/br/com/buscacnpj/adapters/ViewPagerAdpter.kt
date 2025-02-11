package br.com.buscacnpj.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val sessions: List<Fragment>) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        // Certifique-se de que o índice é válido
        if (position < 0 || position >= sessions.size) {
            throw IndexOutOfBoundsException("Invalid position: $position")
        }
        return sessions[position]
    }

    override fun getItemCount(): Int {
        // Número total de fragments
        return sessions.size
    }
}
