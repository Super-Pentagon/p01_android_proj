<?php
  ##回调方法

$img_url = $_GET['img_url'];

// 返回数据给回调页面

echo "
<script>window.parent.document.getElementById('cover_img_url').value='{$img_url}';</script>
";
?>