package com.nwq.code.kotlintestapplication

/**
create by: 86136
create time: 2020/12/25 13:52
Function description:
 */


object TestA {

    val str ="http://198.18.37.197:9898/file/download?session=HS_HBCbycldwXlxZo4hFOBfb8yZrWZfuH&share_path_type=2&path=%2FARDOWNLOAD%2FOne.Last.Deal.2018.FINNISH.1080p.BluRay.x264.DTS-CHD%2FOne.Last.Deal.2018.FINNISH.1080p.BluRay.x264.DTS-CHD.mkv"


    fun test(args: Array<String>) {
       print(formatCookMethods(str,"192.14.7.167"))
    }

    private fun formatCookMethods(cookMethodRawString: String,newIp:String): String {
        val regex = Regex("""http://((25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d)\.){3}(25[0-5]|2[0-4]\d|1\d{2}|[1-9]?\d):9898/""")
        val foundMatches = regex.findAll(cookMethodRawString)
        var formatCookMethods: String = cookMethodRawString
        foundMatches.forEach { result ->
            formatCookMethods =
                formatCookMethods.replace(result.value,"http://$newIp:9898/")
        }
        return formatCookMethods
    }

}