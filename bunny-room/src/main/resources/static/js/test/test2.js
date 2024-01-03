$(document).ready(function()
{


    let trendsSlider = $('.bbb_slider');





    if (latestRooms && trendsSlider.length) {
        latestRooms.forEach(function(room) {
            var newItem = $(
                '<div class="owl-item">' +
                    '<div class="bbb_item is_new">' +
                        '<div class="bbb_content">' +
                            '<div class="bbb_category">' + room.categoryName + '</a></div>' +

                            '<div class="bbb_info clearfix">' +
                                '<div class="bbb_name"><a href="#">' + room.roomName + '</a></div><br>' +
                                '<div class="bbb_price">' + room.price + '원/인' + '</div>' +
                            '</div>' +

                            '<div class="bbb_category"><a href="#">한줄소개: ' + room.description + '</a></div>' +
                        '</div>' +

                        '<ul class="bbb_marks">' +
                            '<li class="bbb_mark bbb_new">new</li>' +
                        '</ul>' +
                        '<div class="bbb_fav"><i class="fas fa-heart"></i></div>' +
                    '</div>' +
                '</div>');

            // 해당방 상세 페이지
            newItem.find('.bbb_name a').attr('url', '/search/roomDetail?roomNo=' + room.roomNo);

            newItem.find('.bbb_name a').on('click', function(event) {
                event.preventDefault(); // 기본 이벤트 방지
                let url = $(this).attr('url');
                window.location.href = url; // 해당 URL로 이동
            });

            trendsSlider.append(newItem);
        });
    }




    if($('.bbb_slider').length)
    {
        trendsSlider = $('.bbb_slider');
        trendsSlider.owlCarousel(
            {
                loop:false,
                margin:30,
                nav:false,
                dots:false,
                autoplayHoverPause:true,
                autoplay:false,
                responsive: {
                    0: { items: 1 },
                    575: { items: 2 },
                    991: { items: 3 }
                }
            });

        trendsSlider.on('click', '.bbb_fav', function (ev)
        {
            $(ev.target).toggleClass('active');
        });

        if($('.bbb_prev').length)
        {
            var prev = $('.bbb_prev');
            prev.on('click', function()
            {
                trendsSlider.trigger('prev.owl.carousel');
            });
        }

        if($('.bbb_next').length)
        {
            var next = $('.bbb_next');
            next.on('click', function()
            {
                trendsSlider.trigger('next.owl.carousel');
            });
        }
    }





});