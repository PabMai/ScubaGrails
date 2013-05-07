package scubagrails

import static org.joda.time.DateTimeConstants.SUNDAY
import static org.joda.time.DateTimeConstants.MONDAY
import static org.joda.time.DateTimeConstants.TUESDAY
import static org.joda.time.DateTimeConstants.WEDNESDAY
import static org.joda.time.DateTimeConstants.THURSDAY
import static org.joda.time.DateTimeConstants.FRIDAY
import static org.joda.time.DateTimeConstants.SATURDAY


class CalendarTagLib {
    static namespace = "calendar"

    def daysOfWeek = {attr, body ->
        def days = [            
            [key: MONDAY, value: 'Lundi'],
            [key: TUESDAY, value: 'Mardi'],
            [key: WEDNESDAY, value: 'Mercredi'],
            [key: THURSDAY, value: 'Jeudi'],
            [key: FRIDAY, value: 'Vendredi'],
            [key: SATURDAY, value: 'Samedi'],
			[key: SUNDAY, value: 'Dimanche']
        ]
        
        def selectedDays = attr.selectedDays
        def name = attr.name

        days.eachWithIndex { def day, int index ->
            def id = "${name}_${index}"
            
            out << g.checkBox(name: name, id: id, value: day.key, checked: (selectedDays?.contains(day.key)), title: day.value)
            out << "<label for='${id}'>${day.value[0..0]}</label>"
        }
    }

}
