function getGithubInfo(user) {

    var username='https://api.github.com/users/'+user;
    console.log(username);
    $.ajax({
        type: "GET",
        url: username,
        dataType: 'json',

    }).done(function(data){
        showUser(data);

    }).fail(function(){
        console.log("Some error Happened");
        noSuchUser(user);
    });

}

function showUser(user) {

    document.getElementById('image').src=user.avatar_url;
    document.getElementById('txtname').innerText=user.name;
    document.getElementById('txtid').innerText=user.id;
    document.getElementById('txturl').href=user.url;
    document.getElementById('txturl').innerText=user.html_url;
    document.getElementById('txtrepository').innerText=user.public_repos;
    document.getElementById('txtfollowers').innerText=user.followers;
    document.getElementById('txtfollowing').innerText=user.following;
    document.getElementById('txtcompany').innerText=user.company;
}
function noSuchUser(username) {
    if(data.message == "Not Found" || username == '') {
        alert("User not found");
    }
}
$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        if (e.which == 13) {
            username = $(this).val();
            $(this).val("");
            getGithubInfo(username);

        }
    })
});