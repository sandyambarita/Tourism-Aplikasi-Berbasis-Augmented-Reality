<?php

use yii\helpers\Html;


/* @var $this yii\web\View */
/* @var $model frontend\models\TCheckpoint */

$this->title = 'Create Checkpoint';
$this->params['breadcrumbs'][] = ['label' => 'Checkpoint', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tcheckpoint-create">

    <h1><?= Html::encode($this->title) ?></h1>

    <?= $this->render('_form', [
        'model' => $model,
    ]) ?>

</div>
