package com.tamerlan.schedulebgtu


import org.junit.Test
import java.util.*


class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//         var i = 0
//        var a = 1
//        var b =2
//        var c =3
//     var observable =  Flowable.just(a,b,c)
//         .doOnNext{println("doOnNext 1")}
//         .map {
//            it*3
//         }.filter(Predicate {
//             it<=6
//         })

        var month: Int
        val date = GregorianCalendar()
        month = date.get(Calendar.YEAR)

        println("Current month is  $month")

    }





}


