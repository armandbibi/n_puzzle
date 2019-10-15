
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

  var display_resolve = (solution) => {
    
    let table = $("#originalBoard");
    console.log(solution.solvedPuzzle.path);
    solution.solvedPuzzle.path.forEach(function() {
      swapPiece("originalBoard", $(this),"-");
    })
  }

  // ajax request to submit form

  var resolve = () => {

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
        console.log(result);
        display_resolve(result);
      },
      error : function(error) {
      console.log(error)}
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

  var getSolutionBoard = (model, dimension) => {

    $.ajax({
      url : '/v1/n-puzzle/board/solution?name=SORTEDTABLE&dimension=' + dimension,
      type : 'GET',
      error : function(xhr, status, error) {
      	console.log(error);
      },
      success : function(data) {
      	drawBoard("expectedBoard", data);
      }
    })
  }

  var setBoard = (model, dimension, emplacement) => {

    $.ajax({
      url : '/v1/n-puzzle/board/solution?name=' + model + '&dimension=' + dimension,
      type : 'GET',
      error : function(xhr, status, error) {
      	console.log(error);
      },
      success : function(data) {
      	drawBoard(emplacement, data);
      	$("#originalBoard > div > div").click(function() {
          changePieceByHand($(this));
        })
        $(".run_puzzle").click(function () {
                resolve();
         })
      }
    })
  }

  var swapPiece = (board, piece1, piece2, dimension) => {

    domElement = $('#' + board);
    let tile1;
    let tile2;
    domElement.children('div').each(function(i) {
        $(this).children('div').each(function(j) {
          
          if($(this).html() == piece1)
            tile1 = $(this);
          if($(this).html() == piece2)
            tile2 = $(this);
        })
    })
    tile1.html(piece2);
    tile2.html(piece1);
  }
  

  $(document).ready(function() {

    getSolutionBoard("CLASSIC", 4);
    setBoard("EASYSHUFFLED_SORTEDTABLE", 4, "originalBoard");
  });
