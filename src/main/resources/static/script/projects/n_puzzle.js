
      var token = $("meta[name='_csrf']").attr("content");
      var header = $("meta[name='_csrf_header']").attr("content");
  // allow user controls

    var toggleUserAction = null;
    function changePieceByHand(piece) {

        if (toggleUserAction == null) {
          toggleUserAction = piece;
        }
        else {
          if (toggleUserAction[0].classList.contains("taq_cell_active") || piece[0].classList.contains("taq_cell_active")) {
            toggleUserAction.toggleClass("taq_cell_active");
            piece.toggleClass("taq_cell_active");
          }
          let tmp = piece.html();
          piece.html(toggleUserAction.html());
          toggleUserAction.html(tmp);
          toggleUserAction = null;
        }
    }

  var display_solve = (solution) => {

    let table = $("#originalBoard");
    console.log(solution.solvedPuzzle.path);
     $(".solution_display").html("");
    $.wait(function() {iterate(0, solution.solvedPuzzle.path), 0.32});
  }
  function iterate(index, array) {
     swapPiece("originalBoard",  array[index],".", 4);
     addPieceToSolution(array[index]);
       $.wait(function() {iterate(++index, array)}, 3);
  }

  function addPieceToSolution(piece) {
    $(".solution_display").append("<div style='display:none; min-height: 50px; border:solid; ' class=' col col-2 timmytest'>" + piece +"</div>");
    $(".timmytest").fadeIn();
  }

  $.wait = function( callback, seconds){
     return window.setTimeout( callback, seconds * 100 );
  }

  // ajax request to submit form

  var solve = () => {
   return new Promise(function (resolve, reject) {
    var formData = {
        heuristicFunction : $("#form_heuristic").html(),
        dimension : $('#form_dimension').html(),
        initialBoard : convertDomTableToBidimentionalArray(4, "originalBoard"),
        expectedBoard : convertDomTableToBidimentionalArray(4, "expectedBoard")
    }
    $.ajax({
      type :'POST',
      url : '/v1/n-puzzle/solve',
      contentType : 'application/json',
      data : JSON.stringify(formData),
      ajaxOptions: {
        beforeSend: function (xhr)
        {
          xhr.setRequestHeader(token, header);
        }
      },
      dataType : 'json',
      success : function(result) {
        resolve(result);
      },
      error : function(error) {
        reject(error);
    }
    })
  })
}
  // convert dom element to bidimentional array

  var convertDomTableToBidimentionalArray= (dimension, location) => {

    let table = [];
    domElement = $('#' + location);
    domElement.children('div').each(function(i) {
    table.push([])
            $(this).children('div').each(function(j) {
              table[i][j] = $(this).children('div').children('div').html();
              if (table[i][j] == '.')
                table[i][j] = 0;
            })
        })
     return table
  }

  var drawBoard = (emplacement, table) => {

  	let data = "";

  	for (let i = 0; i < table.length; i++) {
  	  data += '<div class="taquin_row">'
  	  for (let j = 0; j < table.length; j++) {
  	  	data += '<div class="taq_cell ' +
  	  	        (table[i][j] == 0 ? 'taq_cell_active' : '') +
  	  	        '"><div><div>' +
  	  			(table[i][j] == 0 ? '.' : table[i][j]) +
  	  			'</div></div></div>'
  	  }
  	  data += '</div>';
  	}
  	$("#"+emplacement).html(data);
  }

  var setBoard = (model, dimension, emplacement) => {
    return new Promise (function (resolve, reject) {
        $.ajax({
          url : '/v1/n-puzzle/board/solution?name=' + model + '&dimension=' + dimension,
          type : 'GET',
          error : function(xhr, status, error) {
            console.log(error);
          },
          success : function(data) {
            drawBoard(emplacement, data);
            resolve(data);
          },
          error : function (error) {
            reject : error;
          }
        })
    })
  }

  var swapPiece = (board, piece1, piece2, dimension) => {

    domElement = $('#' + board);
    let tile1;
    let tile2;
    domElement.children('div').each(function(i) {
        $(this).children('div').each(function(j) {
          if($(this).children().children().html() == piece1 + "")
            tile1 = $(this);
          if($(this).children().children().html() == piece2 + "")
            tile2 = $(this);
        })
    })

    let tmp = tile1.html();
    let tmp2 = tile2.html();
    tile1.html(tmp2);
    tile1.toggleClass("taq_cell_active");
    tile2.toggleClass("taq_cell_active");
    tile2.html(tmp);
  }
  $(document).ready(function() {

    setBoard("SORTEDTABLE", 4, "expectedBoard");

     // EASYSHUFFLED_SORTEDTABLE
    setBoard("EASYSHUFFLED_SORTEDTABLE", 4, "originalBoard").then(function(content) {
      $(".taq_cell").click(function(piece) {changePieceByHand($(this));});
      $(".run_puzzle").click(function() {
        $.notify({
          message: 'problem solved'
        },{
          type: 'info'
        });
        $(".run_puzzle").off("click");
        solve().then(function (data) {
          display_solve(data).then(function () {$(".run_puzzle").click();})
        })
      });
    });
  });