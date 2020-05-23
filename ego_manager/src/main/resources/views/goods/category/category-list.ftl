<!-- 设置项目根路径全局变量 -->
<#assign ctx=request.contextPath/>

<!DOCTYPE html>
<html>
<head>
    <#include "../../head.ftl">
    <meta name="__hash__" content="de25d42444751b4d059ec9006eb4ebb9_18e7b463027ae778e1bc48f3dec702d7"/>
</head>
<body style="background-color:#ecf0f5;">

<div class="wrapper">
    <div class="breadcrumbs" id="breadcrumbs">
        <ol class="breadcrumb">
            <li><a href="javascript:void();"><i class="fa fa-home"></i>&nbsp;&nbsp;后台首页</a></li>

            <li><a href="javascript:void();">商品管理</a></li>
            <li><a href="javascript:void();">商品分类</a></li>
        </ol>
    </div>

    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <nav class="navbar navbar-default">
                            <div class="collapse navbar-collapse">
                                <div class="navbar-form row">
                                    <div class="col-md-1">
                                        <button class="btn bg-navy" type="button" onclick="tree_open(this);"><i
                                                    class="fa fa-angle-double-down"></i>展开
                                        </button>
                                    </div>
                                    <div class="col-md-9">
                                        <span class="warning">温馨提示：顶级分类（一级大类）设为推荐时才会在首页楼层中显示</span>
                                    </div>
                                    <div class="col-md-2">
                                        <a href="${ctx}/category/add" class="btn btn-primary pull-right">
											<i class="fa fa-plus"></i>新增分类
										</a>
                                    </div>
                                </div>
                            </div>
                        </nav>
                    </div><!-- /.box-header -->
                    <div class="box-body">
                        <div class="row">
                            <div class="col-sm-12">
                                <table id="list-table" class="table table-bordered table-striped dataTable" role="grid"
                                       aria-describedby="example1_info">
                                    <thead>
                                    <tr role="row">
                                        <th valign="middle">分类ID</th>
                                        <th valign="middle">分类名称</th>
                                        <th valign="middle">手机显示名称</th>
                                        <th valign="middle">是否推荐</th>
                                        <th valign="middle">是否显示</th>
                                        <th valign="middle">分组</th>
                                        <th valign="middle">排序</th>
                                        <th valign="middle">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr role="row" align="center" class="1" id="1_1">
                                        <td>1</td>
                                        <td align="left" style="padding-left:5em">
                                            <span class="glyphicon glyphicon-plus btn-warning"
                                                  style="padding:2px; font-size:12px;" id="icon_1_1" aria-hidden="false"
                                                  onclick="rowClicked(this)"></span>&nbsp; <span>手机 、 数码 、 通信</span>
                                        </td>
                                        <td><span>数码产品</span></td>
                                        <td>
                                            <img width="20" height="20" src="${ctx}/images/yes.png"
                                                 onclick="changeTableVal('goods_category','id','1','is_hot',this)"/>
                                        </td>
                                        <td>
                                            <img width="20" height="20" src="${ctx}/images/yes.png"
                                                 onclick="changeTableVal('goods_category','id','1','is_show',this)"/>
                                        </td>
                                        <td>
                                            <input type="text"
                                                   onchange="updateSort('goods_category','id','1','cat_group',this)"
                                                   onkeyup="this.value=this.value.replace(/[^\d]/g,'')"
                                                   onpaste="this.value=this.value.replace(/[^\d]/g,'')" size="4"
                                                   value="0" class="input-sm"/>
                                        </td>
                                        <td>
                                            <input type="text"
                                                   onchange="updateSort('goods_category','id','1','sort_order',this)"
                                                   onkeyup="this.value=this.value.replace(/[^\d]/g,'')"
                                                   onpaste="this.value=this.value.replace(/[^\d]/g,'')" size="4"
                                                   value="50" class="input-sm"/>
                                        </td>
                                        <td>
                                            <a class="btn btn-primary" href="商品分类-编辑.html"><i class="fa fa-pencil"></i></a>
                                            <a class="btn btn-danger"
                                               href="javascript:del_fun('/index/Admin/Goods/delGoodsCategory/id/1');"><i
                                                        class="fa fa-trash-o"></i></a>
                                        </td>
                                    </tr>
                                    <tr role="row" align="center" class="2" id="2_12" style="display:none">
                                        <td>12</td>
                                        <td align="left" style="padding-left:10em">
                                            <span class="glyphicon glyphicon-plus btn-warning"
                                                  style="padding:2px; font-size:12px;" id="icon_2_12"
                                                  aria-hidden="false" onclick="rowClicked(this)"></span>&nbsp; <span>手机配件</span>
                                        </td>
                                        <td><span>手机配件</span></td>
                                        <td>
                                            <img width="20" height="20" src="${ctx}/images/cancel.png"
                                                 onclick="changeTableVal('goods_category','id','12','is_hot',this)"/>
                                        </td>
                                        <td>
                                            <img width="20" height="20" src="${ctx}/images/yes.png"
                                                 onclick="changeTableVal('goods_category','id','12','is_show',this)"/>
                                        </td>
                                        <td>
                                            <input type="text"
                                                   onchange="updateSort('goods_category','id','12','cat_group',this)"
                                                   onkeyup="this.value=this.value.replace(/[^\d]/g,'')"
                                                   onpaste="this.value=this.value.replace(/[^\d]/g,'')" size="4"
                                                   value="0" class="input-sm"/>
                                        </td>
                                        <td>
                                            <input type="text"
                                                   onchange="updateSort('goods_category','id','12','sort_order',this)"
                                                   onkeyup="this.value=this.value.replace(/[^\d]/g,'')"
                                                   onpaste="this.value=this.value.replace(/[^\d]/g,'')" size="4"
                                                   value="50" class="input-sm"/>
                                        </td>
                                        <td>
                                            <a class="btn btn-primary"
                                               href="/index/Admin/Goods/addEditCategory/id/12"><i
                                                        class="fa fa-pencil"></i></a>
                                            <a class="btn btn-danger"
                                               href="javascript:del_fun('/index/Admin/Goods/delGoodsCategory/id/12');"><i
                                                        class="fa fa-trash-o"></i></a>
                                        </td>
                                    </tr>
                                    <tr role="row" align="center" class="3" id="3_100" style="display:none">
                                        <td>100</td>
                                        <td align="left" style="padding-left:15em">
                                            <span>电池 电源 充电器</span>
                                        </td>
                                        <td><span>电池 电源 充电器</span></td>
                                        <td>
                                            <img width="20" height="20" src="${ctx}/images/cancel.png"
                                                 onclick="changeTableVal('goods_category','id','100','is_hot',this)"/>
                                        </td>
                                        <td>
                                            <img width="20" height="20" src="${ctx}/images/yes.png"
                                                 onclick="changeTableVal('goods_category','id','100','is_show',this)"/>
                                        </td>
                                        <td>
                                            <input type="text"
                                                   onchange="updateSort('goods_category','id','100','cat_group',this)"
                                                   onkeyup="this.value=this.value.replace(/[^\d]/g,'')"
                                                   onpaste="this.value=this.value.replace(/[^\d]/g,'')" size="4"
                                                   value="0" class="input-sm"/>
                                        </td>
                                        <td>
                                            <input type="text"
                                                   onchange="updateSort('goods_category','id','100','sort_order',this)"
                                                   onkeyup="this.value=this.value.replace(/[^\d]/g,'')"
                                                   onpaste="this.value=this.value.replace(/[^\d]/g,'')" size="4"
                                                   value="50" class="input-sm"/>
                                        </td>
                                        <td>
                                            <a class="btn btn-primary" href="/index/Admin/Goods/addEditCategory/id/100"><i
                                                        class="fa fa-pencil"></i></a>
                                            <a class="btn btn-danger"
                                               href="javascript:del_fun('/index/Admin/Goods/delGoodsCategory/id/100');"><i
                                                        class="fa fa-trash-o"></i></a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-5">
                                <div class="dataTables_info" id="example1_info" role="status" aria-live="polite">分页
                                </div>
                            </div>
                        </div>
                    </div><!-- /.box-body -->
                </div><!-- /.box -->
            </div>
        </div>
    </section>
</div>
<script type="text/javascript">

    // 展开收缩
    function tree_open(obj) {
        var tree = $('#list-table tr[id^="2_"], #list-table tr[id^="3_"] '); //,'table-row'
        if (tree.css('display') == 'table-row') {
            $(obj).html("<i class='fa fa-angle-double-down'></i>展开");
            tree.css('display', 'none');
            $("span[id^='icon_']").removeClass('glyphicon-minus');
            $("span[id^='icon_']").addClass('glyphicon-plus');
        } else {
            $(obj).html("<i class='fa fa-angle-double-up'></i>收缩");
            tree.css('display', 'table-row');
            $("span[id^='icon_']").addClass('glyphicon-minus');
            $("span[id^='icon_']").removeClass('glyphicon-plus');
        }
    }

    // 以下是 bootstrap 自带的  js
    function rowClicked(obj) {
        span = obj;

        obj = obj.parentNode.parentNode;

        var tbl = document.getElementById("list-table");

        var lvl = parseInt(obj.className);

        var fnd = false;

        var sub_display = $(span).hasClass('glyphicon-minus') ? 'none' : '' ? 'block' : 'table-row';
        //console.log(sub_display);
        if (sub_display == 'none') {
            $(span).removeClass('glyphicon-minus btn-info');
            $(span).addClass('glyphicon-plus btn-warning');
        } else {
            $(span).removeClass('glyphicon-plus btn-info');
            $(span).addClass('glyphicon-minus btn-warning');
        }

        for (i = 0; i < tbl.rows.length; i++) {
            var row = tbl.rows[i];

            if (row == obj) {
                fnd = true;
            } else {
                if (fnd == true) {
                    var cur = parseInt(row.className);
                    var icon = 'icon_' + row.id;
                    if (cur > lvl) {
                        row.style.display = sub_display;
                        if (sub_display != 'none') {
                            var iconimg = document.getElementById(icon);
                            $(iconimg).removeClass('glyphicon-plus btn-info');
                            $(iconimg).addClass('glyphicon-minus btn-warning');
                        } else {
                            $(iconimg).removeClass('glyphicon-minus btn-info');
                            $(iconimg).addClass('glyphicon-plus btn-warning');
                        }
                    } else {
                        fnd = false;
                        break;
                    }
                }
            }
        }

        for (i = 0; i < obj.cells[0].childNodes.length; i++) {
            var imgObj = obj.cells[0].childNodes[i];
            if (imgObj.tagName == "IMG") {
                if ($(imgObj).hasClass('glyphicon-plus btn-info')) {
                    $(imgObj).removeClass('glyphicon-plus btn-info');
                    $(imgObj).addClass('glyphicon-minus btn-warning');
                } else {
                    $(imgObj).removeClass('glyphicon-minus btn-warning');
                    $(imgObj).addClass('glyphicon-plus btn-info');
                }
            }
        }

    }
</script>
</body>
<script src="${ctx}/js/category.list.js" type="text/javascript"></script>
</html>