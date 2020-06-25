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
        console.log("Error Occured");
        noSuchUser(user);
    });

}

function showUser(user) {

    document.getElementById('image').src=user.avatar_url;
    document.getElementById('Name').innerText=user.name;
    document.getElementById('ID').innerText=user.id;
    document.getElementById('URL').href=user.url;
    document.getElementById('URL').innerText=user.html_url;
    document.getElementById('Repository').innerText=user.public_repos;
    document.getElementById('Followers').innerText=user.followers;
    document.getElementById('Following').innerText=user.following;
    document.getElementById('Company').innerText=user.company;
}
function noSuchUser(username) {
    if(data.message == "Not Found" || username == '') {
        alert("We cannot find the User");
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
