package com.tamerlan.schedulebgtu.domain

import com.tamerlan.schedulebgtu.data.DayOfWeek
import com.tamerlan.schedulebgtu.data.Lesson
import com.tamerlan.schedulebgtu.data.Sequence
import com.tamerlan.schedulebgtu.data.TimetableDayOfWeek
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.lang.NullPointerException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ParseSchedule @Inject constructor() {
    private var document: Document? = null


    private fun parse() {
        try {
            document = Jsoup.connect("https://www.tu-bryansk.ru/education/schedule").get()
        } catch (e: Exception) {
            throw e
        }
    }


    private fun parse(id: String): List<String> {
        parse()
        val list: ArrayList<String> = ArrayList()

        val s = document?.select(id)

        s?.map {
            list.add(it.text())
        }

        return list
    }

    fun getLevel() = parse("#level")
    fun getFaculty() = parse("#faculty")


    fun getGroups(
        faculty: String,
        level: String,
        period: String,
        form: String,
        number: String
    ): List<String> {
        try {
            val list: ArrayList<String> = ArrayList()
            document = Jsoup.connect(
                "https://www.tu-bryansk.ru/education/schedule/schedule.ajax.php?namedata=group&" +
                        "faculty=$faculty" +
                        "&level=$level" +
                        "&period=$period" +
                        "&form=$form"
            ).get()

            val s = document?.select("option")

            //TODO() add exception processing

            if (s == null || s.isEmpty())
                throw NullPointerException("Response is empty")

            s.removeAt(0)

            s.map {
                if (it.text().contains(number))
                    list.add(it.text())
            }

            return list
        } catch (e: Exception) {
            throw  e
        }
    }


    fun getSchedule(group: String, period: String, form: String): ArrayList<TimetableDayOfWeek> {
        try {
            val list: ArrayList<TimetableDayOfWeek> = ArrayList()
            document = Jsoup.connect(
                "https://www.tu-bryansk.ru/education/schedule/schedule.ajax.php?" +
                        "namedata=$group&" +
                        "&period=$period" +
                        "&form=$form"
            ).get()


            val s = document?.select("td")

            if (s == null || s.isEmpty())
                throw NullPointerException("Response is empty")



            var prevTime = false

            s.map {
                when(it.className())
                {
                    "daeweek" ->  {
                        list.add(
                            TimetableDayOfWeek(
                                when(it.text())
                                {
                                    "Понедельник" -> DayOfWeek.Monday
                                    "Вторник" -> DayOfWeek.Tuesday
                                    "Среда" -> DayOfWeek.Wednesday
                                    "Четвер" -> DayOfWeek.Monday
                                    "Пятница" -> DayOfWeek.Friday
                                    "Суббота" -> DayOfWeek.Saturday
                                    else -> DayOfWeek.Sunday
                                },
                                ArrayList(),it.text()
                            ))
                    }
                    "itmles schtime" -> {
                        prevTime = false
                        list.last().lessons.add(Lesson(0,"","","",Sequence.all,"",list.last().title))
                        list.last().lessons.last().time = it.text()
                    }
                    "itmles schname" -> {
                        prevTime = !prevTime

                        if (it.text().isEmpty() && prevTime)
                        {
                            list.last().lessons.last().sequence = Sequence.odd
                        }
                        else if(it.text().isEmpty())
                        {
                            list.last().lessons.last().sequence = Sequence.odd
                        } else {
                            list.last().lessons.last().name = it.text()
                        }
                    }
                    "itmles schteacher" ->{
                         if(it.text().isNotEmpty())
                        {
                            list.last().lessons.last().teacher = it.text()
                        } else {

                         }
                    }
                    "itmles schclass" -> {
                        if(it.text().isNotEmpty())
                        {
                            list.last().lessons.last().position = it.text()
                        } else {

                        }
                    }
                    else -> {}
                }




            }

            return list
        } catch (e: Exception) {
            throw  e
        }
    }
}