function saved1() {
    let arr1 = document.getElementById("arr1");
    let arr2 = document.getElementById("arr2");

    let pattern = /^\[[0-9]+(,[0-9]+(?:[0-9]+)?)*\]$/;

    if(pattern.test(arr1.value) && pattern.test(arr2.value)){
        $.ajax({
            type: 'POST',
            url: '/arrays/'+arr1.value+"/"+arr2.value,
            error : function() {
                console.log("error");
            },
            success: function (data) {
                if(data){
                    document.getElementById("resultado_d1").innerText = data;
                }
            }
        });
    }else{
        if(!pattern.test(arr1.value)){
            alert("Array de busca incorreto, considere o padrão [1,2,3,4,...]");
        }
        if(!pattern.test(arr2.value)){
            alert("Array alvo incorreto, considere o padrão [1,2,3,4,...]");
        }
    }

}
function saved2() {
    let word = document.getElementById("char1");

    $.ajax({
        type: 'POST',
        url: '/char/'+word.value,
        error : function() {
            console.log("error");
        },
        success: function (data) {
            if(data !== 0)
                document.getElementById("resultado_d2").innerText = String.fromCharCode(parseInt(data));
            else
                document.getElementById("resultado_d2").innerText = "' '";
        }
    });

}