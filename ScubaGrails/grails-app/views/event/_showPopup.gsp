<%@ page import="org.joda.time.Instant" %>

<div class="eventPopup">

<h2>${eventInstance.title}</h2>
<p class="date">
    Début : <g:formatDate date="${new Instant(occurrenceStart).toDate()}" format="E, dd MMMM à HH:mm"/>
<br />
    Fin : <g:formatDate date="${new Instant(occurrenceEnd).toDate()}" format="E, dd MMMM à HH:mm"/>
</p>
<p>
    <g:link action="show" id="${eventInstance.id}" params="[occurrenceStart: occurrenceStart, occurrenceEnd: occurrenceEnd]">Plus de détails »</g:link>
</p>
</div>