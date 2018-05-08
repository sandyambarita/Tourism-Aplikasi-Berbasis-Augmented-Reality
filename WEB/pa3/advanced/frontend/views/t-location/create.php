<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model frontend\models\TLocation */

$this->title = 'Create Location';
$this->params['breadcrumbs'][] = ['label' => 'Location', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tlocation-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
