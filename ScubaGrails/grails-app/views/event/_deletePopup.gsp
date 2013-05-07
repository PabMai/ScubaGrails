<div id="deletePopup" style="display: none;">

<g:form action="delete">
    <g:hiddenField name="id" value="${eventInstance.id}" />
    <g:hiddenField name="occurrenceStart" value="${occurrenceStart}" />

    <p>Voulez-vous supprimer seulement cet évènement ou tous les évènements de la série ?</p>

    <table>
        <tbody>
        <tr>
            <td><button type="submit" name="deleteType" value="occurrence">Uniquement cet événement</button></td>
            <td>Les autres événements de la série resteront identiques.</td>
        </tr>

        <tr>
            <td><button type="submit" name="deleteType" value="following">Tous les événements ultérieurs</button></td>
            <td>Cet événement et tous les événements suivants seront supprimés.</td>
        </tr>
        <tr>
            <td><button type="submit" name="deleteType" value="all">Tous les événements de la série</button></td>
            <td>Tous les événements de la série seront supprimés.</td>
        </tr>
        </tbody>

    </table>

</div>


</g:form>

</div>

