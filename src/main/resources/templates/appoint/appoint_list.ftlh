<html>
<span>[来訪リスト]</span>
<table style="border-collapse: collapse;" cellspacing="1" cellpadding="5">
    <tbody>
        <tr align="center">
            <td style="background-color:#edf5f9;border:1px solid #42B4ED;font-weight: bold;" width="25%">appointeeId</td>
            <td style="background-color:#edf5f9;border:1px solid #42B4ED;font-weight: bold;" width="25%">requesterId</td>
            <td style="background-color:#edf5f9;border:1px solid #42B4ED;font-weight: bold;" width="10%">startTime</td>
            <td style="background-color:#edf5f9;border:1px solid #42B4ED;font-weight: bold;" width="10%">endTime</td>
            <td style="background-color:#edf5f9;border:1px solid #42B4ED;font-weight: bold;" width="40%">place</td>
        </tr>

        <!-- Output rows from java.util.List -->
        <#list appointList as appoint>
        <tr>
            <!-- Object.fieldでObjectのgetterが呼ばれる -->
            <td style="border: 1px solid #42B4ED;">${appoint.appointeeId}</td>
            <td style="border: 1px solid #42B4ED;">${appoint.requesterId}</td>
            <td style="border: 1px solid #42B4ED;">${appoint.startTime}</td>
            <td style="border: 1px solid #42B4ED;">${appoint.endTime}</td>
            <td style="border: 1px solid #42B4ED;">${appoint.place}</td>
        </tr>
        </#list>
    </tbody>
</table>
</html>