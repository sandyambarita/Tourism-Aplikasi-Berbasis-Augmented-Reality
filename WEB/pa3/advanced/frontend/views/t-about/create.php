<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model frontend\models\TAbout */

$this->title = 'Create About';
$this->params['breadcrumbs'][] = ['label' => 'About', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tabout-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
