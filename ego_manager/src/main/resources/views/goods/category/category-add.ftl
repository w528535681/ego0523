<!-- 设置项目根路径全局变量 -->
<#assign ctx=request.contextPath/>

<!DOCTYPE html>
<html>
<head>
    <#include "../../head.ftl">
    <meta name="__hash__" content="3089b9badfca8307d7d520487d125ae4_6385f66dff50b4c04db3ec79b8a9d245"/>4
</head>
<body style="background-color:#ecf0f5;">


<div class="wrapper">
    <div class="breadcrumbs" id="breadcrumbs">
        <ol class="breadcrumb">
            <li><a href="javascript:void();"><i class="fa fa-home"></i>&nbsp;&nbsp;后台首页</a></li>

            <li><a href="javascript:void();">商品管理</a></li>
            <li><a href="javascript:void();">添加修改分类</a></li>
        </ol>
    </div>

    <section class="content">
        <div class="row">
            <div class="col-sm-12">
                <div class="box">
                    <div class="box-header">
                        <h3 class="box-title">增加分类</h3>
                        <div class="pull-right">
                            <a href="javascript:history.go(-1)" data-toggle="tooltip" title="" class="btn btn-default"
                               data-original-title="返回"><i class="fa fa-reply"></i></a>
                            <a href="javascript:;" class="btn btn-default"
                               data-url="http://www.ego.cn/Doc/Index/article/id/1006/developer/user.html"
                               onclick="get_help(this)"><i class="fa fa-question-circle"></i> 帮助</a>
                        </div>
                    </div>

                    <!-- /.box-header -->
                    <form action="/index/Admin/Goods/addEditCategory" method="post" class="form-horizontal"
                          id="category_form">
                        <div class="box-body">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">分类名称</label>
                                <div class="col-sm-6">
                                    <input type="text" placeholder="名称" class="form-control large" name="name" value="">
                                    <span class="help-inline" style="color:#F00; display:none;" id="err_name"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2">手机分类名称</label>
                                <div class="col-sm-6">
                                    <input type="text" placeholder="手机分类名称" class="form-control large"
                                           name="mobile_name" value="">
                                    <span class="help-inline" style="color:#F00; display:none;"
                                          id="err_mobile_name"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label0 class="control-label col-sm-2">上级分类</label0>

                                <div class="col-sm-3">
                                    <select name="parent_id_1" id="parent_id_1"
                                            onchange="getCategory(this.value,'parent_id_2','0' );"
                                            class="small form-control">
                                        <option value="0">顶级分类</option>
                                        <#list gcList as gc>
                                            <option value="${gc.id}">${gc.name}</option>
                                        </#list>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                    <select name="parent_id_2" id="parent_id_2" class="small form-control">
                                        <option value="0">请选择商品分类</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2">导航显示</label>

                                <div class="col-sm-10">
                                    <label>
                                        <input checked="checked" type="radio" name="is_show" value="1"> 是
                                        <input type="radio" name="is_show" value="0"> 否

                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2">分类分组:</label>

                                <div class="col-sm-1">
                                    <select name="cat_group" id="cat_group" class="form-control">
                                        <option value="0">0</option>
                                        <option value='1'>1</option>
                                        "
                                        <option value='2'>2</option>
                                        "
                                        <option value='3'>3</option>
                                        "
                                        <option value='4'>4</option>
                                        "
                                        <option value='5'>5</option>
                                        "
                                        <option value='6'>6</option>
                                        "
                                        <option value='7'>7</option>
                                        "
                                        <option value='8'>8</option>
                                        "
                                        <option value='9'>9</option>
                                        "
                                        <option value='10'>10</option>
                                        "
                                        <option value='11'>11</option>
                                        "
                                        <option value='12'>12</option>
                                        "
                                        <option value='13'>13</option>
                                        "
                                        <option value='14'>14</option>
                                        "
                                        <option value='15'>15</option>
                                        "
                                        <option value='16'>16</option>
                                        "
                                        <option value='17'>17</option>
                                        "
                                        <option value='18'>18</option>
                                        "
                                        <option value='19'>19</option>
                                        "
                                        <option value='20'>20</option>
                                        "
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-sm-2">分类展示图片</label>

                                <div class="col-sm-10">
                                    <input onclick="GetUploadify(1,'image','category');" type="button" value="上传图片"/>
                                    <input type="text" value="" name="image" id="image" class="form-control large"
                                           readonly="readonly" style="width:500px;display:initial;"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2">显示排序</label>
                                <div class="col-sm-1">
                                    <input type="text" placeholder="50" class="form-control large" name="sort_order"
                                           value=""/>
                                    <span class="help-inline" style="color:#F00; display:none;"
                                          id="err_sort_order"></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2">分佣比例</label>
                                <div class="col-sm-1">
                                    <input type="text" placeholder="50" class="form-control large"
                                           name="commission_rate" id="commission_rate" value="0"
                                           onpaste="this.value=this.value.replace(/[^\d.]/g,'')"
                                           onkeyup="this.value=this.value.replace(/[^\d.]/g,'')"/>
                                </div>
                                <div class="col-sm-1" style="margin-top: 6px;margin-left: -20px;">
                                    <span>%</span>
                                </div>
                            </div>
                        </div>
                        <div class="box-footer">
                            <input type="hidden" name="id" value="">
                            <button type="reset" class="btn btn-primary pull-left"><i class="icon-ok"></i>重填</button>
                            <button type="button"
                                    onclick="ajax_submit_form('category_form','/index/Admin/Goods/addEditCategory/is_ajax/1');"
                                    class="btn btn-primary pull-right"><i class="icon-ok"></i>提交
                            </button>
                        </div>
                        <input type="hidden" name="__hash__"
                               value="3089b9badfca8307d7d520487d125ae4_6385f66dff50b4c04db3ec79b8a9d245"/>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
<script>
    /** 以下是编辑时默认选中某个商品分类*/
    $(document).ready(function () {

    });
</script>
<script>
    /**
     * 获取多级联动的商品分类
     * id:当前选择框的值
     * next：下级选择框显示的内容
     * select_id:
     */
    function getCategory(id, next, select_id) {
        var url = '${ctx}/category/'+id;
        //console.log(url);
// 用户重新选择顶级分类时，重置下级分类为：请选择商品分类，且清空下级分类信息
        var htmlStr = "<option value='0'>请选择商品分类</option>";
        if(0==id){
            $("#"+next).html(htmlStr);
            return;
        }
        $.ajax({
            type: "GET",
            url: url,
            error: function (request) {
                layer.alert("获取子分类失败！");
            },
            success: function (result) {
                if (result.length > 0) {
                    for (i = 0; i < result.length; i++) {
                        htmlStr += "<option value='" + result[i].id + "'>"
                            + result[i].name + "</option>"
                    }
                    $("#"+next).html(htmlStr);
                }else {
                    layer.alert("获取子分类失败！");
                }
            }
        });
    }
</script>

</body>
</html>