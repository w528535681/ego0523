function delfunc(obj){
    layer.confirm('确认删除？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
                type : 'post',
                url : $(obj).attr('data-url'),
                data : {act:'del',del_id:$(obj).attr('data-id')},
                dataType : 'json',
                success : function(data){
                    if(data==1){
                        layer.msg('操作成功', {icon: 1});
                        $(obj).parent().parent().remove();
                    }else{
                        layer.msg(data, {icon: 2,time: 2000});
                    }
                    layer.closeAll();
                }
            })
        }, function(index){
            layer.close(index);
            return false;// 取消
        }
    );
}

//全选
function selectAll(name,obj){
    $('input[name*='+name+']').prop('checked', $(obj).checked);
}

function get_help(obj){
    layer.open({
        type: 2,
        title: '帮助手册',
        shadeClose: true,
        shade: 0.3,
        area: ['90%', '90%'],
        content: $(obj).attr('data-url'),
    });
}

function delAll(obj,name){
    var a = [];
    $('input[name*='+name+']').each(function(i,o){
        if($(o).is(':checked')){
            a.push($(o).val());
        }
    })
    if(a.length == 0){
        layer.alert('请选择删除项', {icon: 2});
        return;
    }
    layer.confirm('确认删除？', {btn: ['确定','取消'] }, function(){
            $.ajax({
                type : 'get',
                url : $(obj).attr('data-url'),
                data : {act:'del',del_id:a},
                dataType : 'json',
                success : function(data){
                    if(data == 1){
                        layer.msg('操作成功', {icon: 1});
                        $('input[name*='+name+']').each(function(i,o){
                            if($(o).is(':checked')){
                                $(o).parent().parent().remove();
                            }
                        })
                    }else{
                        layer.msg(data, {icon: 2,time: 2000});
                    }
                    layer.closeAll();
                }
            })
        }, function(index){
            layer.close(index);
            return false;// 取消
        }
    );
}