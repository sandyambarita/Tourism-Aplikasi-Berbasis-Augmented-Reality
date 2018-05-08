<?php

use yii\helpers\Html;

/* @var $this yii\web\View */
/* @var $model frontend\models\TLocation */

$this->title = 'Update Location: ' . $model->location_id;
$this->params['breadcrumbs'][] = ['label' => 'Location', 'url' => ['index']];
$this->params['breadcrumbs'][] = ['label' => $model->location_id, 'url' => ['view', 'id' => $model->location_id]];
$this->params['breadcrumbs'][] = 'Update';
?>
<div class="tlocation-update">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
