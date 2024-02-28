#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_cn_edu_bistu_cs_se_jnitest_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_cn_edu_bistu_cs_se_jnitest_MainActivity_myJNITest(JNIEnv *env, jobject thiz) {
    std::string hello = "test";
    return env->NewStringUTF(hello.c_str());
}