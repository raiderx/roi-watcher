function loadTemplate(element) {
    return _.template( $(element).text().replace('<![CDATA[', '').replace(']]>', '') );
}

function fetch(page, size) {
    $.get(APP_URLS.previews, { page: page, size: size }).done(function(response) {
        var renderPreviews = loadTemplate( $('#tpl-previews') );
        $('#table').html( renderPreviews({ previews: response.content }) );

        var renderPager = loadTemplate( $('#tpl-pager') );
        $('#pager').html( renderPager({ page: response.number, pages: response.totalPages }) );

        $('a.page').on('click', function(event) {
            var page = parseInt( $(event.target).data('page') );
            fetch(page, 30);
        });
    });
}
