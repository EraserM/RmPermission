package me.rmpermission.library

import androidx.fragment.app.FragmentActivity

object RmPermission {

    private const val TAG = "VirtualFragment"

    fun request(activity: FragmentActivity, vararg permissions: String, callback: PermissionCallback) {
        val fm = activity.supportFragmentManager
        val existedFragment = fm.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as VirtualFragment
        } else {
            val virtualFragment = VirtualFragment()
            fm.beginTransaction().add(virtualFragment, TAG).commitNow()
            virtualFragment
        }
        fragment.request(callback, *permissions)
    }

}