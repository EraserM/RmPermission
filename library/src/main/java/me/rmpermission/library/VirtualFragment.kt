package me.rmpermission.library

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import java.security.Permission

/**
 * @ClassName:   VirtualFragment
 * @Description:
 * @Author:      Eraser
 * @Date:        2020/5/5 10:28
 */
class VirtualFragment : Fragment() {

    private var callback: (PermissionCallback) ?= null

    fun request(block: PermissionCallback, vararg permissions: String) {
        callback = block
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (1 == requestCode) {
            val denied = ArrayList<String>()
            grantResults.forEach {
                if (PackageManager.PERMISSION_GRANTED != grantResults[it])
                    denied.add(permissions[it])
            }
            val allGrant = denied.isEmpty()
            callback?.let {
                it(allGrant, denied)
            }
        }
    }

}

typealias PermissionCallback = (Boolean, List<String>) -> Unit