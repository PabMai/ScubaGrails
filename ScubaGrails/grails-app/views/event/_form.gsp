<script>
jQuery(function($){
	$.datepicker.regional['fr'] = {
		closeText: 'Fermer',
		prevText: 'Précédent',
		nextText: 'Suivant',
		currentText: 'Aujourd\'hui',
		monthNames: ['Janvier','Février','Mars','Avril','Mai','Juin',
		'Juillet','Août','Septembre','Octobre','Novembre','Décembre'],
		monthNamesShort: ['Janv.','Févr.','Mars','Avril','Mai','Juin',
		'Juil.','Août','Sept.','Oct.','Nov.','Déc.'],
		dayNames: ['Dimanche','Lundi','Mardi','Mercredi','Jeudi','Vendredi','Samedi'],
		dayNamesShort: ['Dim.','Lun.','Mar.','Mer.','Jeu.','Ven.','Sam.'],
		dayNamesMin: ['D','L','M','M','J','V','S'],
		weekHeader: 'Sem.',
		dateFormat: 'dd/mm/yy',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: ''};
	$.datepicker.setDefaults($.datepicker.regional['fr']);
});
</script>

<%@ page import="scubagrails.Event" %>
<%@ page import="scubagrails.EventRecurType" %>
<%@ page import="org.joda.time.Instant" %>


<g:set var="entityName" value="${message(code: 'event.label', default: 'Event')}" />

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'title', 'error')}">
    <label for="title"><g:message code="event.title.label" default="Titre" /></label>
    <g:textField name="title" value="${eventInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'startTime', 'error')}">
    <label for="startTime"><g:message code="event.startTime.label" default="Date de début" /></label>
    <g:textField name="startTime"
         value="${formatDate(date: occurrenceStart ? new Instant(occurrenceStart).toDate() : eventInstance?.startTime, format: 'dd/MM/yyyy hh:mm a')}"
         class="datetime" />
</div>


<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'endTime', 'error')}">
    <label for="endTime"><g:message code="event.endTime.label" default="Date de fin" /></label>

    <g:textField name="endTime"
         value="${formatDate(date: occurrenceEnd ? new Instant(occurrenceEnd).toDate() : eventInstance?.endTime, format: 'dd/MM/yyyy hh:mm a')}"
         class="datetime" />

        
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'location', 'error')}">
    <label for="location"><g:message code="event.location.label" default="Lieu" /></label>
    <g:textField name="location" value="${eventInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'isRecurring', 'error')}">

    <label for="isRecurring"><g:message code="event.isRecurring.label" default="Récurrence" /></label>
    <g:checkBox name="isRecurring" value="${eventInstance.isRecurring}" />
    <span id="recurDescription"></span>

    <a id="editRecurringLink" ${eventInstance.isRecurring ?  "" : 'style="display:none"'} href="#">Editer</a>

</div>



<div class="fieldcontain ${hasErrors(bean: eventInstance, field: 'description', 'error')}">
    <label for="description"><g:message code="event.description.label" default="Description" /></label>
    <g:textArea name="description"  value="${eventInstance?.description}" />
</div>


<div id="recurPopup"></div>
<div id="recurOptions" style="display:none" >
    <div>
        <label>Récurrent:</label>
        <g:select name="recurType" from="${EventRecurType.values()}" optionValue="name" value="${eventInstance?.recurType}"/>
    </div>

    <div>
        <label>Répéter tous les:</label>
        <g:select name="recurInterval" from="${1..30}" value="${eventInstance?.recurInterval}" />
        <span id="repeatLabel"></span>
    </div>

    <div id="weeklyOptions" ${eventInstance.recurType != EventRecurType.WEEKLY ? 'style="display:none"' : ''}>
        <label>Répéter le: </label>
        <div class="options">
            <calendar:daysOfWeek name="recurDaysOfWeek" selectedDays="${eventInstance?.recurDaysOfWeek}" />
        </div>
    </div>

    <div><label>Fin:</label>
        <div class="input">
            <input id="recurEndOption1" name="recurEndOption" type="radio" group="recurEndOption" ${(!eventInstance.recurCount && !eventInstance.recurUntil) ? 'checked="checked"' : ''} value="never" />
            <label for="recurEndOption1">Jamais</label><br />

            <input id="recurEndOption2" name="recurEndOption" type="radio" group="recurEndOption" ${(eventInstance.recurCount) ? 'checked="checked"' : ''} value="occurrences" />
            <label for="recurEndOption2">Après <g:textField name="recurCount" size="3" value="${eventInstance?.recurCount}" /> fois</label><br/>

            <input id="recurEndOption3" name="recurEndOption" type="radio" group="recurEndOption" ${(!eventInstance.recurCount && eventInstance.recurUntil) ? 'checked="checked"' : ''} value="endDate" />
            <label for="recurEndOption3">Le <g:textField name="recurUntil" size="8" value="${formatDate(date: (eventInstance?.recurCount ? null : eventInstance?.recurUntil), format: 'dd/MM/yyyy HH:mm')}" /></label>


        </div>
    </div>

    <div>
            <strong><span id="recurSummary"></span></strong>

    </div>

</div>
