$.ajax({url: "http://localhost:8080/populateData"})

function makeCalls() {
    $('#caller_but').prop('disabled', true);
    $(document).ready(function() {
        $.ajax({
            url: "http://localhost:8080/makeCalls"
        });
    });
}

window.setInterval(function(){
    $.get("http://localhost:8080/getData", function (data) {
        var html = "";
        html += '<br><br><tr>'
        jQuery.each(data, function(idx, row) {
            html += "<td>" + idx + "    :   </td>" + "<td>" + row + "</td>" + "<br>"
        })
        html += '</tr>';
        document.getElementById("text-area").innerHTML = html;
    })
}, 10);
