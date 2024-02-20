package com.example.skeleton.model.request.signin

import com.example.skeleton.model.ktor.EndPoints
import com.example.skeleton.model.request.BaseRequestFactory
import com.example.skeleton.model.request.BaseRequestParam

class SignInRequestFactory(signInParam: SignInParam = SignInParam()) : BaseRequestFactory() {
    override fun getEndPoint(): String = EndPoints.SIGN_IN
    override var baseRequestParam: BaseRequestParam = signInParam
}