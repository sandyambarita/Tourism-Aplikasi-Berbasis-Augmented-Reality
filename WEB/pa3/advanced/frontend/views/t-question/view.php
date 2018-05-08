<?php

use yii\helpers\Html;
use yii\widgets\DetailView;

/* @var $this yii\web\View */
/* @var $model frontend\models\TQuestion */

$this->title = $model->question_id;
$this->params['breadcrumbs'][] = ['label' => 'Question', 'url' => ['index']];
$this->params['breadcrumbs'][] = $this->title;
?>
<div class="tquestion-view">

    <h1><?= Html::encode($this->title) ?></h1>

    <p>
        <?= Html::a('Update', ['update', 'id' => $model->question_id], ['class' => 'btn btn-primary']) ?>
        <?= Html::a('Delete', ['delete', 'id' => $model->question_id], [
            'class' => 'btn btn-danger',
            'data' => [
                'confirm' => 'Are you sure you want to delete this item?',
                'method' => 'post',
            ],
        ]) ?>
    </p>

    <?= DetailView::widget([
        'model' => $model,
        'attributes' => [
            'question_id',
            'challenge_type_id',
            'checkpoint_id',
            'no_soal',
            'question',
            'option1',
            'option2',
            'option3',
            'option4',
            'answer',
        ],
    ]) ?>

</div>
