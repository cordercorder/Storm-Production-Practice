window.onload=function(){
    $(document).ready(function () {
        $("#table_page").bootstrapTable({
            columns:[{
                field:'charactor',
                title:'字符'
            },{
                field:'count',
                title:'字符出现次数'
            },{
                field:'frequency',
                title:'字符出现频率'
            }]
        });
    });

    $(document).ready(function () {
        $("#table_page2").bootstrapTable({
            columns:[{
                field:'charactor',
                title:'字符'
            },{
                field:'frequency',
                title:'字符出现频率'
            },{
                field:'huffmancode',
                title:'哈夫曼编码'
            }]
        });
    });
}