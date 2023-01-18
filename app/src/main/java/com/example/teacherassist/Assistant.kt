package com.example.teacherassist

class Assistant() {

    fun setStudentId(id: Int){
        studentId = id
    }

    fun setCourseId(id: Int){
        courseId = id
    }

    fun setConnectionId(id: Int){
        connectionId = id
    }

    fun setoriginalPage(oP: Int){
        originalPage = oP
    }

    fun getStudentId(): Int{
        return studentId
    }

    fun getCourseId(): Int{
        return courseId
    }

    fun getConnectionId(): Int{
        return connectionId
    }

    fun getOriginalPage(): Int{
        return originalPage
    }

    companion object{
        var studentId = 0
        var courseId = 0
        var connectionId = 0
        var originalPage = 0
    }
}