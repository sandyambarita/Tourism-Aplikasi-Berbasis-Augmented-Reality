<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model frontend\models\TAchievement */

$this->title = 'Create Achievement';
$this->params['breadcrumbs'][] = ['label' => 'Achievement', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tachievement-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
