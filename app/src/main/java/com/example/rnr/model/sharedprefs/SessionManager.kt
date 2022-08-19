package com.example.rnr.model.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import com.example.rnr.model.dataclasses.authme.AuthMeResponse

class SessionManager() {

    var userSession: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var context: Context? = null

    companion object{
        val SESSION_USER = "userLoginSession"
        val KEY_AUTHTOKEN = "authme"
        val KEY_USERNAME = "username"
        val KEY_USERTENANTID = "usertenantid"
        private const val IS_LOGIN = "isloggedin"
    }

    //SessionManager
    constructor(_context: Context, sessionName: String?) :this(){
        context = _context
        userSession = context!!.getSharedPreferences(sessionName, Context.MODE_PRIVATE)
        editor = userSession!!.edit()
    }

    //UserLoginSession
    fun creatingUserSession(
        username:String?,
        tenantid:String?
    ) {
        editor!!.putString(KEY_USERNAME,username)
        editor!!.putString(KEY_USERTENANTID,tenantid)
        editor!!.commit()
    }

    fun getUserDetailFromSession(): HashMap<String, String?>? {
        val userdata = HashMap<String, String?>()
        userdata[KEY_USERNAME] =
            userSession!!.getString(
                KEY_USERNAME,
                null
            )

        userdata[KEY_USERTENANTID] =
            userSession!!.getString(
                KEY_USERTENANTID,
                null
            )

        return userdata
    }

    fun creatingTokenSession(
        token: String?
    ) {
        editor!!.putBoolean(IS_LOGIN, true)
        editor!!.putString(KEY_AUTHTOKEN,token)
        editor!!.commit()
    }

    fun getTokenFromSession(): HashMap<String, String?>? {
        val userdata = HashMap<String, String?>()
        userdata[KEY_AUTHTOKEN] =
            userSession!!.getString(
                KEY_AUTHTOKEN,
                null
            )

        return userdata
    }

    //Login
    fun checkLogin(): Boolean {
        return userSession!!.getBoolean(
            IS_LOGIN,
            false
        )
    }

    fun logout() {
        editor!!.clear()
        editor!!.commit()
    }
}