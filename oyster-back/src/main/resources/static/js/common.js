// for searching box
$("#searchBar").keypress(function(e){
    if (e.code == 13)
        $("#searchBarSubmit").click();
});
