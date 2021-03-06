function loadTemplate(element) {
    return _.template( $(element).text().replace('<![CDATA[', '').replace(']]>', '') );
}

function search(title, page, size) {
    $.get(APP_URLS['previews'], { title: title, page: page, size: size }).done(function(response) {
        var renderPreviews = loadTemplate( $('#tpl-previews') );
        $('#previews').html( renderPreviews({ previews: response.content }) );

        var renderPager = loadTemplate( $('#tpl-pager') );
        $('#pager').html( renderPager({ page: response.number, pages: response.totalPages }) );

        $('a.page').on('click', function(event) {
            var page = parseInt( $(event.target).data('page') );
            search(title, page, APP_PROPERTIES['petitionsPerPage']);
        });
    });
}

function initIndexPage() {
    search('', 0, APP_PROPERTIES['petitionsPerPage']);

    $('button[name=search]').on('click', function() {
        search( $('[name=title]').val(), 0, APP_PROPERTIES['petitionsPerPage'] );
    });
    $('input[name=title]').on('keypress', function(event) {
       if (event.which == 13) {
           search( $(event.target).val(), 0, APP_PROPERTIES['petitionsPerPage'] );
       }
    });
}